package org.dows.log.entity.log;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;*/

/**
 * @author Administrator
 * @date 2023/2/23 16:24
 */
/*@Entity
@Table(name = "user_entity_audit_log")*/
@TableName("user_entity_audit_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestLog {

    /*@Id
    @Column(nullable = false, columnDefinition = "int(38) comment '主键id'")*/
    private Long id;
    //@Query(type = Query.Type.INNER_LIKE)
    private String methodParams;
    private String methodDescr;
    //@Query(type = Query.Type.INNER_LIKE)
    private String methodName;
    private String methodResult;
    private String ip;
    //@Query(type = Query.Type.INNER_LIKE)
    private String accountName;
    private String browser;
    //@Query(type = Query.Type.INNER_LIKE)
    private String userName;
    private String deviceId;

}
