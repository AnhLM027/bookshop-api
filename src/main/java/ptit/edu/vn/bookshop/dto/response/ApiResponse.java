package ptit.edu.vn.bookshop.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T>{
    private int statusCode;
    private String error;
    private Object message;
    private T data;
}
