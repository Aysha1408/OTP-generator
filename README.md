# OTP-generator
User authentication is done by taking information such as name, phone number and email address. 
 OTP is generated by either SMS or email (according to user preference).
 Code valid for one time use only and for 1 minute.
 Code can be regenerated.


SMS Function
 using API key from SMS service provider called textlocal.
 accessing textlocal through URL.
 API key, message and phone number are sent as parameters to the URL to generate SMS.


Email Function
There are 3 steps here.
Defining SMTP properties
 connecting to Gmail server.
2) Creating Session object
 authenticating from_address@gmail.com
3) Sending an email


