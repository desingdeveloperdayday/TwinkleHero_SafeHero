package kr.hero.app.api.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProtectorDTO implements Serializable {
	private Integer protectorIdx;
	private Integer protectorOrder;
	private String protectorName;
	private Integer protectorMemIdx;
}