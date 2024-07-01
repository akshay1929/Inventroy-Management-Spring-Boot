Inventory Manager Spring Boot

MySQL Installation: 
Install Xampp to run MySQL server
https://www.apachefriends.org/download.html

Once installed, start MySQL server and go to http://localhost/phpmyadmin/.

Create schema named 'shopify' 

Create table named 'product' with 6 columns

Create columns with exact names:

'id' - check Auto incriment box

'productname' - Type: text

'price' - Type: text

'quantity' - Type: text

'description; - - Type: text

'type' - Type: text

Open \shopify\src\main\resources\application.properties

Edit line 1 with the port selected for MySQL server (Default: 3306):

spring.datasource.url=jdbc:mysql://localhost:{port}/shopify?useUnicode=true&useJDBCCompliantTimezone

Right click on \shopify\src\main\java\com\example\shopify\ShopifyApplication.java and click 'Run As' > 'Java Application'

Go to http://localhost:7062/ to run the application
