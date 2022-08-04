/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paypal;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class PaymentService {

    private static final String CLIENT_KEY = "AbTPsdbwWXQqkWddiJ_iSck8llZHt8kDDE0Gs48PQXaR4GqmWapuKemxVOsU3Ziy3lW11GradB6zjTMx";
    private static final String SECRET_KEY = "EKGVIu_4ASHCpbRj57LPWzMtgvKlZPkQHvw4ICUT5dLKBtX8MOjhWo2Livc29ANThdYrpU4GC19H8UxH";
    private static final String MODE = "sandbox";

    // authourize
    public String authourizePayment(OrderDetail orderDetail) throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectUrls();
        List<Transaction> tranlist = getTransactionInformation(orderDetail);

        //set request for payment and add param
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(tranlist)
                .setRedirectUrls(redirectUrls)
                .setPayer(payer)
                .setIntent("authorize");

        //create api context to connect to paypall server
        APIContext apiContext = new APIContext(CLIENT_KEY, "EKGVIu_4ASHCpbRj57LPWzMtgvKlZPkQHvw4ICUT5dLKBtX8MOjhWo2Livc29ANThdYrpU4GC19H8UxH", MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println(approvedPayment.toString());
        return getAprrovalUrl(approvedPayment);

    }

//getPayerInformation
    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");

        //set payer information
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Nguyen")
                .setLastName("quoc Bao")
                .setEmail("Aido@gmail.com");

        payer.setPayerInfo(payerInfo);

        return payer;

    }

    //getTransactionInformation
    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        Details details = new Details();
        details.setShipping(orderDetail.getShipping());
        details.setSubtotal(orderDetail.getSubtotal());
        details.setTax(orderDetail.getTax());

        //amout
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(orderDetail.getTotal());
        amount.setDetails(details);

        //Transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription(orderDetail.getProductName());

        //List of items
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();

        Item item = new Item();
        item.setCurrency("USD")
                .setName(orderDetail.getProductName())
                .setPrice(orderDetail.getSubtotal())
                .setTax(orderDetail.getTax())
                .setQuantity("1");

        //add items into itemList and trasaction
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);

        List<Transaction> tranlist = new ArrayList<>();
        tranlist.add(transaction);

        //return transaction
        return tranlist;

    }

    // get approved url
    private String getAprrovalUrl(Payment approvalPayment) {
        String approvalUrl = null;
        List<Links> links = approvalPayment.getLinks();

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalUrl = link.getHref();
            }
        }
        return approvalUrl;
    }

    // send redirect url when event occured
    private RedirectUrls getRedirectUrls() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/VegetableStrore/error.jsp");
        redirectUrls.setReturnUrl("http://localhost:8080/VegetableStrore/ReviewPayment");

        return redirectUrls;
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException{
        APIContext apiContext = new APIContext(CLIENT_KEY, SECRET_KEY, MODE);
        return Payment.get(apiContext, paymentId);
    }
    public Payment excutePayment(String paymentId, String payerId) throws PayPalRESTException{
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        
        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(CLIENT_KEY, SECRET_KEY, MODE);
        return payment.execute(apiContext, paymentExecution);
    
    }
}
