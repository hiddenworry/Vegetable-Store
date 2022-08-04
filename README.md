# Vegetable-Store

### Installation

1. Create database
   ```sh
   CREATE DATABASE VegetablesManagement
   ```

2. Open Constant.java in folder Vegetable-Store/VegetableStrore/src/java/google/GoogleLoginConstants.java and change value of GOOGLE_CLIENT_ID, GOOGLE_CLIENT_SECRET corresponding to yours from your Google developer account.
   ```sh
   public static final String GOOGLE_CLIENT_ID = "GOOGLE_CLIENT_ID";
   public static final String GOOGLE_CLIENT_SECRET = "GOOGLE_CLIENT_SECRET";
   
   ```
3. Open Constant.java in folder Vegetable-Store/VegetableStrore/src/java/mail/JavaSendMail.java and change value of Email, Password corresponding to yours from your Google developer account.
   ```sh
   public static final String account = "Your emai address";
   public static final String password = "Your email password";
   
   ```

4. Open Constant.java in folder Vegetable-Store/VegetableStrore/src/java/paypal/PaymentService.java and change value of Paypal client key and secret key corresponding to yours from your Paypal sandbox developer account.
   ```sh
    Firstly, to get client and secret key and sand box test account for your own, you must follow up the many step at "https://developer.paypal.com/tools/sandbox/"
    and then pass it into two line below at PaymentService.java
    private static final String CLIENT_KEY = "Your Client key";
    private static final String SECRET_KEY = "Your secret key";
   
   ```

5. Run the project
