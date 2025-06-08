package org.example.expert.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordRequest {

    @NotBlank
    private String oldPassword;

    //새 비밀번호는 8자 이상이어야 함.

    /*
    ^ : 문자열의 시작을 나타냄.
    (?=.*[A-Z] : 대문자 하나 이상 포함
    (?=.*\\d) : 숫자 하나 이상 포함
    $ : 문자열의 끝을 나타냄.
    ?= : 전방 탐색. 조건만 확인
    */
    @NotBlank
    @Size(min = 8)
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).*$",
            message = "새 비밀번호는 8자 이상이어야 하고, 숫자와 대문자를 포함해야 합니다."
    )
    private String newPassword;
}
