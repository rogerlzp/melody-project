
echo "running gateway..."
nohup java  -Xms64m -Xmx256m    -jar service-gateway-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > gateway.log &


echo "running account..."
nohup java  -Xms64m -Xmx256m    -jar account-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > account.log &

sleep 10
echo "running product..."
nohup java  -Xms64m -Xmx256m    -jar service-product-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > product.log &

sleep 10
echo "running web..."
nohup java  -Xms64m -Xmx256m    -jar web-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=prod > web.log &

sleep 10
echo "running admin..."
nohup java  -Xms64m -Xmx256m    -jar admin-0.0.1-SNAPSHOT.jar   -Dspring.profiles.active=prod > admin.log &

sleep 10
echo "running admin-web..."
nohup java  -Xms64m -Xmx256m    -jar admin-web-0.0.1-SNAPSHOT.jar  -Dspring.profiles.active=prod > admin_web.log &