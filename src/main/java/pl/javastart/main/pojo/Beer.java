
package pl.javastart.main.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Beer {

    private Integer id;
    private Float abv;



    }
