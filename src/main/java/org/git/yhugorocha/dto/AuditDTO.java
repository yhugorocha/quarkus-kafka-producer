package org.git.yhugorocha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditDTO implements Serializable {

    private String username;
    private Object objectBefore;
    private Object objectAfter;
    private LocalDateTime dateUpdate = LocalDateTime.now();

    public AuditDTO(String username,Object objectBefore, Object objectAfter){
        this.username = username;
        this.objectBefore = objectBefore;
        this.objectAfter = objectAfter;
    }

    public static AuditDTO fromAuditDTO(String username,Object objectBefore, Object objectAfter){
        return new AuditDTO(username, objectBefore, objectAfter);
    }
}
