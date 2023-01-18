package com.project.shop.feature.code.success;

import com.project.shop.feature.code.Code;
import com.project.shop.feature.code.CodeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "success")
@Data
public class SuccessCode {

    @AllArgsConstructor
    public enum authentication implements Code {
        ;
        private final String code;
        @Override
        public CodeType getCodeType() {
            return CodeType.authentication;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }

    @AllArgsConstructor
    public enum board implements Code {
        ;
        private final String code;

        @Override
        public CodeType getCodeType() {
            return CodeType.board;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }

    @AllArgsConstructor
    public enum main implements Code {
        ;
        private final String code;


        @Override
        public CodeType getCodeType() {
            return CodeType.main;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }

    @AllArgsConstructor
    public enum member implements Code {
        signUpMember("0"),
        loginMember("1"),
        logoutMember("2"),
        updateMember("3"),
        withdrawalMember("4");
        private final String code;


        @Override
        public CodeType getCodeType() {
            return CodeType.member;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }

    @AllArgsConstructor
    public enum product implements Code {
        ;
        private final String code;


        @Override
        public CodeType getCodeType() {
            return CodeType.product;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }

    @AllArgsConstructor
    public enum purchase implements Code {
        ;
        private final String code;

        @Override
        public CodeType getCodeType() {
            return CodeType.purchase;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }

    @AllArgsConstructor
    public enum sell implements Code {
        ;
        private final String code;


        @Override
        public CodeType getCodeType() {
            return CodeType.sell;
        }

        @Override
        public String getCode() {
            return "success.".concat(this.getCodeType().getValue()).concat(".").concat(this.code);
        }

        @Override
        public String getMessageKey() {
            return this.getCode().concat(".message");
        }
    }
}
