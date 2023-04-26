package com.rudkids.rudkids.interfaces.admin;

import com.rudkids.rudkids.common.ControllerTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.admin.AdminControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.*;
import static com.rudkids.rudkids.common.fixtures.product.ProductControllerFixtures.PRODUCT_상태_변경_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AdminControllerTest extends ControllerTest {

    @DisplayName("[유저-검색]")
    @Test
    void 유저를_검색한다() throws Exception {
        given(adminService.searchUser(any(), any()))
            .willReturn(유저_정보_INFO_응답());

        mockMvc.perform(get(ADMIN_USER_DEFAULT_URL + "?email={email}", USER_EMAIL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("admin/searchUser",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                queryParameters(
                    parameterWithName("email")
                        .description("유저 이메일")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[유저-권한변경]")
    @Test
    void 유저_권한을_변경한다() throws Exception {
        willDoNothing()
            .given(adminService)
            .changeUserRole(any(), any(), any());

        mockMvc.perform(patch(ADMIN_USER_DEFAULT_URL + "/{id}", USER_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(유저_권한_변경_요청())))
            .andDo(print())
            .andDo(document("admin/changeUserRole",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("유저 id")
                ),
                requestFields(
                    fieldWithPath("role")
                        .type(JsonFieldType.STRING)
                        .description("유저 권한")
                )
            ))
            .andExpect(status().isOk());
    }

    @Disabled("MockMultipartFile 오류 잡고 나서 테스트 코드 실행")
    @DisplayName("[프로덕트-생성]")
    @Test
    void 프로덕트를_생성한다() throws Exception {
        willDoNothing()
            .given(productService)
            .create(any(), any());

        mockMvc.perform(post(ADMIN_PRODUCT_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_등록_요청()))
            )
            .andDo(print())
            .andDo(document("product/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("productBio")
                        .type(JsonFieldType.STRING)
                        .description("소개글")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[프로덕트-상태변경]")
    @Test
    void 프로덕트_상태를_변경한다() throws Exception {
        willDoNothing()
            .given(productService)
            .changeStatus(any(), any(), any());

        mockMvc.perform(put(ADMIN_PRODUCT_DEFAULT_URL + "/{id}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(PRODUCT_상태_변경_요청()))
            )
            .andDo(print())
            .andDo(document("product/changeStatus",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("프로덕트 id")
                ),
                requestFields(
                    fieldWithPath("productStatus")
                        .type(JsonFieldType.STRING)
                        .description("프로덕트 상태")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[매거진-생성]")
    @Test
    void 매거진을_작성한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .create(any(), any());

        mockMvc.perform(post(ADMIN_MAGAZINE_DEFAULT_URL)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_작성_요청())))
            .andDo(print())
            .andDo(document("magazine/create",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("내용")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[매거진-수정]")
    @Test
    void 매거진을_수정한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .update(any(), any(), any());

        mockMvc.perform(put(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MAGAZINE_수정_요청())))
            .andDo(print())
            .andDo(document("magazine/update",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("매거진 id")
                ),
                requestFields(
                    fieldWithPath("title")
                        .type(JsonFieldType.STRING)
                        .description("새로운 제목"),

                    fieldWithPath("content")
                        .type(JsonFieldType.STRING)
                        .description("새로운 내용")
                )
            ))
            .andExpect(status().isOk());
    }

    @DisplayName("[매거진-삭제]")
    @Test
    void 매거진을_삭제한다() throws Exception {
        willDoNothing()
            .given(magazineService)
            .delete(any(), any());

        mockMvc.perform(delete(ADMIN_MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
            .andDo(print())
            .andDo(document("magazine/delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("매거진 id")
                )
            ))
            .andExpect(status().isOk());
    }
}
