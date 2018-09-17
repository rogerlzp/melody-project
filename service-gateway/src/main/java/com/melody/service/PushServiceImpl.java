package com.melody.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.melody.gateway.api.PushService;
import com.melody.gateway.dto.PushEnter;
import com.melody.gateway.dto.PushResult;
import com.melody.user.api.PushRelationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


//import cn.jpush.api.common.ClientConfig;
//import cn.jpush.api.common.resp.APIConnectionException;
//import cn.jpush.api.common.resp.APIRequestException;
//import cn.jpush.api.push.PushClient;
//import cn.jpush.api.push.model.Platform;
//import cn.jpush.api.push.model.PushPayload;
//import cn.jpush.api.push.model.PushPayload.Builder;
//import cn.jpush.api.push.model.audience.Audience;
//import cn.jpush.api.push.model.audience.AudienceTarget;
//import cn.jpush.api.push.model.notification.AndroidNotification;
//import cn.jpush.api.push.model.notification.IosNotification;
//import cn.jpush.api.push.model.notification.Notification;

/**
 * 
 * Created by zhanggt on 2016-2-18.
 * 
 */
@Service(group = "pushService")
public class PushServiceImpl implements PushService {

	private Log logger = LogFactory.getLog(this.getClass());

//	@Value("${message.push.app.production}")
//	private boolean product;

	@Reference(group = "pushRelationService")
	private PushRelationService pushRelationService;

	@Override
	public PushResult push(PushEnter pushEnter) {
		PushResult pushResult = new PushResult();
		return pushResult;
		/*
		ClientConfig clientConfig = ClientConfig.getInstance();
		clientConfig.setTimeToLive(pushEnter.getTimeToLive());
		clientConfig.setApnsProduction(product);
		PushClient client = new PushClient(PushConstants.MASTER_SECRET, PushConstants.APP_KEY);
		Builder builder = PushPayload.newBuilder();
		PushPayload pushPayload = null;
		cn.jpush.api.push.model.notification.Notification.Builder notificationBuilder = Notification.newBuilder();
		if (pushEnter.getPlatform() == null || pushEnter.getPlatform().isEmpty()) {
			builder.setPlatform(Platform.all());
			notificationBuilder.addPlatformNotification(IosNotification.newBuilder().setAlert(pushEnter.getAlert())
					.addExtras(pushEnter.getExtras()).build());
			notificationBuilder.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushEnter.getAlert())
					.addExtras(pushEnter.getExtras()).build());
			builder.setNotification(notificationBuilder.build());
		} else {
			if (pushEnter.getPlatform().contains("android") && pushEnter.getPlatform().contains("ios")) {
				builder.setPlatform(Platform.all());
				notificationBuilder.addPlatformNotification(IosNotification.newBuilder().setAlert(pushEnter.getAlert())
						.addExtras(pushEnter.getExtras()).build());
				notificationBuilder.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushEnter.getAlert())
						.addExtras(pushEnter.getExtras()).build());
				builder.setNotification(notificationBuilder.build());
			} else if (pushEnter.getPlatform().contains("android")) {
				builder.setPlatform(Platform.android());
				notificationBuilder.addPlatformNotification(AndroidNotification.newBuilder().setAlert(pushEnter.getAlert())
						.addExtras(pushEnter.getExtras()).build());
				builder.setNotification(notificationBuilder.build());
			} else if (pushEnter.getPlatform().contains("ios")) {
				builder.setPlatform(Platform.ios());
				notificationBuilder.addPlatformNotification(IosNotification.newBuilder().setAlert(pushEnter.getAlert())
						.addExtras(pushEnter.getExtras()).build());
				builder.setNotification(notificationBuilder.build());
			}
		}

		cn.jpush.api.push.model.audience.Audience.Builder audienceBuilder = Audience.newBuilder();
		int i = 0;
		if (pushEnter.getTag() != null && !pushEnter.getTag().isEmpty()) {
			audienceBuilder.addAudienceTarget(AudienceTarget.tag(pushEnter.getTag()));
			i++;
		}
		if (pushEnter.getTagAnd() != null && !pushEnter.getTagAnd().isEmpty()) {
			audienceBuilder.addAudienceTarget(AudienceTarget.tag_and(pushEnter.getTagAnd()));
			i++;
		}
		if (pushEnter.getAlias() != null && !pushEnter.getAlias().isEmpty()) {
			audienceBuilder.addAudienceTarget(AudienceTarget.alias(pushEnter.getAlias()));
			i++;
		}
		if (pushEnter.getUserId() != null && !pushEnter.getUserId().isEmpty()) {
			pushEnter.getRegistrationId().addAll(pushRelationService.getDevice(pushEnter.getUserId()));
			i++;
		}
		if (pushEnter.getRegistrationId() != null && !pushEnter.getRegistrationId().isEmpty()) {
			audienceBuilder.addAudienceTarget(AudienceTarget.registrationId(pushEnter.getRegistrationId()));
			i++;
		}
		if (i == 0) {
			if (pushEnter.getFlag() != null && pushEnter.getFlag().equalsIgnoreCase("1")) {//wang
				builder.setAudience(Audience.all());
			}else{
				if (logger.isInfoEnabled()) {
					logger.info("push message cancel, because of the all_send_param is disabled.");
				}
				pushResult.setCode("0");
				return pushResult;
			}
		} else {
			builder.setAudience(audienceBuilder.build());
		}
		pushPayload = builder.build();
		try {
			cn.jpush.api.push.PushResult pr = client.sendPush(pushPayload);
			if (logger.isInfoEnabled()) {
				logger.info("push message successful, message:" + pushEnter + " response:" + JSON.toJSONString(pr));
			}
			pushResult.setCode("0");
			return pushResult;
		} catch (APIConnectionException e) {
			logger.error("PushService.push APIConnectionException", e);
			throw new BusinessException(BusinessCodes.GATEWAY_PUSH_CONNECTION_FAILURE);
		} catch (APIRequestException e) {
			logger.error("PushService.push APIRequestException", e);
			throw new BusinessException(BusinessCodes.GATEWAY_PUSH_REQUEST_FAILURE);
		}
		*/
	}


}
