package kr.hero.app.api.model;


//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	private Integer memIdx;
	private String memName;
	private String memPhone;
	private String memRegtime;
	private Integer memProtectorNum;
	//@JsonIgnore  private MultipartFile thumbnailImageFile;
}