package formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import message.LogMessage;

import java.util.Map;

public class JsonLogFormatter implements LogFormatter {

    @Override
    public String format(LogMessage logMsg) {

        String jsonStr = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonStr = mapper.writeValueAsString(logMsg);
        } catch (Exception e) {
            System.out.println("Exception occurred while trying to convert a log msg into a json string");
        }
        return jsonStr;

    }

}
