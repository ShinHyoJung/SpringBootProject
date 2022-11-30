package com.project.shop.feature.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CodeType {
    authentication("0"),
    board("1"),
    main("2"),
    member("3"),
    product("4"),
    purchase("5"),
    sell("6"),
    ;

    private final String value;
}
