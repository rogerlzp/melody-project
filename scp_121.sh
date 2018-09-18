
scp account/target/account-0.0.1-SNAPSHOT.jar roger@121.42.145.216:/home/roger/work/egg/
scp admin-web/target/admin-web-0.0.1-SNAPSHOT.jar  roger@121.42.145.216:/home/roger/work/egg/
scp service-gateway/target/service-gateway-0.0.1-SNAPSHOT.jar  roger@121.42.145.216:/home/roger/work/egg/

scp service-product/target/service-product-0.0.1-SNAPSHOT.jar  roger@121.42.145.216:/home/roger/work/egg/

scp web/target/web-0.0.1-SNAPSHOT.jar  roger@121.42.145.216:/home/roger/work/egg/

scp admin/target/admin-0.0.1-SNAPSHOT.jar roger@121.42.145.216:/home/roger/work/egg/


 nohup java -jar service-gateway-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > gateway.log &

 nohup java -jar account-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > account.log &

 nohup java -jar service-product-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > product.log &
 nohup java -jar web-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > web.log &
 nohup java -jar service-gateway-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > gateway.log &
 nohup java -jar service-gateway-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > gateway.log &

account-0.0.1-SNAPSHOT.jar  admin-web-0.0.1-SNAPSHOT.jar  service-gateway-0.0.1-SNAPSHOT.jar  web-0.0.1-SNAPSHOT.jar
admin-0.0.1-SNAPSHOT.jar    cert                          service-product-0.0.1-SNAPSHOT.jar
~
~