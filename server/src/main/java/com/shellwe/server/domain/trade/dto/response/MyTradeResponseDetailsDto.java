package com.shellwe.server.domain.trade.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MyTradeResponseDetailsDto {

    List<ShellForMyTradeResponseDetails> shells;
}
