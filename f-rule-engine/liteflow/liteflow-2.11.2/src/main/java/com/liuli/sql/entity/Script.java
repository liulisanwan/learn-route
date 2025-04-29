package com.liuli.sql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author hui-zhang
 * @since 2023-06-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Script implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String applicationName;

    private String scriptId;

    private String scriptName;

    private String scriptData;

    private String scriptType;


}
