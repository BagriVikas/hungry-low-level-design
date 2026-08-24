package formatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import message.LogMessage;

import java.util.Map;

public class JsonLogFormatter implements LogFormatter {

    @Override
    public String format(LogMessage logMsg) {

        String jsonStr = "";

        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            jsonStr = mapper.writeValueAsString(logMsg);

        } catch (Exception e) {
            System.out.println(
                    "Exception occurred while trying to convert a log msg into a json string: "
                            + e.getMessage()
            );
        }

        return jsonStr;
    }

}
