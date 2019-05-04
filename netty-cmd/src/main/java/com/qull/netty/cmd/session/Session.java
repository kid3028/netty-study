package com.qull.netty.cmd.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author kzh
 * @Date 2019/5/3 2:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    private String userId;

    private String userName;
}
