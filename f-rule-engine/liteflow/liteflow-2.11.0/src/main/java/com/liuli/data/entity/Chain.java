package com.liuli.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Chain implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String applicationName;

    private String chainName;

    private String chainDesc;

    private String elData;

    private LocalDateTime createTime;


}
