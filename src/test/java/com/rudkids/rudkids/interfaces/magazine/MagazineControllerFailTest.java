package com.rudkids.rudkids.interfaces.magazine;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.magazine.exception.MagazineNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.rudkids.rudkids.common.fixtures.magazine.MagazineControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MagazineControllerFailTest extends ControllerTest {

    @DisplayName("존재하지 않는 매거진을 수정할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_매거진을_수정할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
                .when(magazineService)
                .update(any(), any(), any());

        mockMvc.perform(put(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(MAGAZINE_수정_요청())))
                .andDo(print())
                .andDo(document("magazine/update/failByNotFoundError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        pathParameters(
                                parameterWithName("id")
                                        .description("존재하지 않는 매거진 id")
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
                .andExpect(status().isNotFound());
    }

    @DisplayName("존재하지 않는 매거진을 삭제할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_매거진을_삭제할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
                .when(magazineService)
                .delete(any(), any());

        mockMvc.perform(delete(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID)
                        .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE))
                .andDo(print())
                .andDo(document("magazine/delete/failByNotFoundError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("JWT Access Token")
                        ),
                        pathParameters(
                                parameterWithName("id")
                                        .description("존재하지 않는 매거진 id")
                        )
                ))
                .andExpect(status().isNotFound());
    }

    @DisplayName("존재하지 않는 매거진을 상세조회할 경우 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_매거진을_상세조회할_경우_상태코드_404를_반환한다() throws Exception {
        doThrow(new MagazineNotFoundException())
                .when(magazineService)
                .find(any());

        mockMvc.perform(get(MAGAZINE_DEFAULT_URL + "/{id}", MAGAZINE_ID))
                .andDo(print())
                .andDo(document("magazine/find/failByNotFoundError",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id")
                                        .description("존재하지 않는 매거진 id")
                        )
                ))
                .andExpect(status().isNotFound());
    }
}