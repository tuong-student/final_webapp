package com.final_project.model;

import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentService {
    private static final String CLIENT_ID = "AfmzaBCCeAc6gZgPlVousJyItXK34PN6BZrL55GITrSvBSPTyAcgqhvoDIiJAIJn4Dii63WGh9UqpR-W";
    private static final String CLIENT_SECRET = "ENJVWAHrx9VRwxxkSb_rLrD5nxy_nZ7UoCWQdCct3U6iRH6saX_YioTwhm9pyfeklWLd419jOzI62Xm6";
    private static final String MODE = "sandbox";

    public String authorizePayment(OrderDetail order, HttpServletRequest req) throws PayPalRESTException {
        Payer payer = getPayerInformations(req);
        RedirectUrls redirectUrls = getRedirectUrls(req);
        List<Transaction> listTransactions = getTransactionInformation(order);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransactions)
                .setRedirectUrls(redirectUrls)
                .setPayer(payer)
                .setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvePayment = requestPayment.create(apiContext);

        System.out.println("Approval link!");
        return getApprovalLink(approvePayment);
    }

    private String getApprovalLink(Payment approvePayment) {
        List<Links> links = approvePayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }
        return approvalLink;
    }

    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubtotal());
        details.setTax(orderDetail.getTax());

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD")
                .setPrice(orderDetail.getSubtotal())
                .setTax(orderDetail.getTax())
                .setQuantity("1");

        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> ListTransaction = new ArrayList<Transaction>();
        ListTransaction.add(transaction);
        return ListTransaction;
    }

    private RedirectUrls getRedirectUrls(HttpServletRequest request) {
        RedirectUrls redirectUrls = new RedirectUrls();
        String baseURL = "https://webcuoiky.herokuapp.com/";
        redirectUrls.setCancelUrl(baseURL + "/checkout.jsp");
        redirectUrls.setReturnUrl(baseURL + "/ReviewPaymentServlet");
        return redirectUrls;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return payment.execute(apiContext, paymentExecution);
    }

    private Payer getPayerInformations(HttpServletRequest req) {
        Payer payer = new Payer();
        User user = (User) req.getSession().getAttribute("user");
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setLastName(user.getname())
                .setEmail(user.getemail());
        payer.setPayerInfo(payerInfo);
        return payer;
    }
}
