package com.rudkids.api.order;

import com.rudkids.api.common.ControllerTest;
import com.rudkids.core.order.exception.OrderNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.rudkids.api.common.fixtures.OrderControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerFailTest extends ControllerTest {

    @DisplayName("[주문-상세조회-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문을_상세조회_시_예외가_발생한다() throws Exception {
        doThrow(new OrderNotFoundException())
            .when(orderService)
            .get(any());

        mockMvc.perform(get(ORDER_DEFAULT_URL + "/{id}", orderId))
            .andDo(print())
            .andDo(document("order/find/failByNotFoundError",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 주문 id")
                ),
                responseFields(Error_응답_필드())
            ))
            .andExpect(status().isNotFound());
    }

    @DisplayName("[주문-취소-OrderNotFoundException]")
    @Test
    void 존재하지_않는_주문을_취소_시_예외가_발생한다() throws Exception {
        doThrow(new OrderNotFoundException())
            .when(orderService)
            .delete(any());

        mockMvc.perform(delete(ORDER_DEFAULT_URL + "/{id}", orderId)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
            )
            .andDo(print())
            .andDo(document("order/delete",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("JWT Access Token")
                ),
                pathParameters(
                    parameterWithName("id")
                        .description("존재하지 않는 주문 id")
                ),
                responseFields(Error_응답_필드())
            ))
            .andExpect(status().isNotFound());
    }
}