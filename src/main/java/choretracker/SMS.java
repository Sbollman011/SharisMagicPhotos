package choretracker;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@SuppressWarnings("checkstyle")

public class SMS {
  // Find your Account Sid and Token at twilio.com/user/account
  public static final String ACCOUNT_SID = "AC91931690d7610dc174fa8db8f998d862";
  public static final String AUTH_TOKEN = "9616d680052faae37b39c3e782c434c8";

  public static void sendSMS(String msg) {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    Message message = Message.creator(new PhoneNumber("+12068194589"),
        new PhoneNumber("+12058518276"),
        msg).create();

        message.getSid();
  }
}
