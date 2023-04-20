package com.rudkids.rudkids.interfaces.item;

import com.rudkids.rudkids.common.ControllerTest;
import com.rudkids.rudkids.domain.item.domain.ItemStatus;
import com.rudkids.rudkids.domain.item.exception.ItemNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.rudkids.rudkids.common.fixtures.item.ItemControllerFixtures.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ItemControllerTest extends ControllerTest {

    @DisplayName("아이템을 등록한다.")
    @Test
    void 아이템을_등록한다() throws Exception {
        willDoNothing()
            .given(itemService)
            .create(any(), any(), any());

        mockMvc.perform(post(ITEM_DEFAULT_URL + "/{productId}", 프로덕트_아이디)
                .header(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(ITEM_등록_요청()))
            ).andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("아이템 상세정보를 조회한다.")
    @Test
    void 아이템_상세정보를_조회한다() throws Exception {
        given(itemService.find(any()))
            .willReturn(ITEM_상세정보_조회_응답());

        mockMvc.perform(get(ITEM_DEFAULT_URL + "/detail/{id}", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 아이템 상세정보를 조회할 때 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_아이템_상세정보를_조회할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .find(any());

        mockMvc.perform(get(ITEM_DEFAULT_URL + "/{id}/detail", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @DisplayName("아이템을_재발매한다.")
    @Test
    void 아이템을_재발매한다() throws Exception {
        given(itemService.changeOnSales(any()))
            .willReturn(ItemStatus.ON_SALES.name());

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}/on-sales", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 아이템을 재발매 할 때 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_아이템을_재발매_할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .changeOnSales(any());

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}/on-sales", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @DisplayName("아이템을_판매종료한다.")
    @Test
    void 아이템을_판매종료한다() throws Exception {
        given(itemService.changeEndOfSales(any()))
            .willReturn(ItemStatus.END_OF_SALES.name());

        mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}/end-of-sales", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 아이템을 판매종료 할 때 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_아이템을_판매종료_할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .changeEndOfSales(any());

        mockMvc.perform(delete(ITEM_DEFAULT_URL + "/{id}/end-of-sales", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isNotFound());
    }

    @DisplayName("아이템을 준비중 상태로 변경한다.")
    @Test
    void 아이템을_준비중_상태로_변경한다() throws Exception {
        given(itemService.changePrepare(any()))
            .willReturn(ItemStatus.PREPARE.name());

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}/prepare", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @DisplayName("존재하지 않는 아이템을 준비중 상태로 변경 할 때 상태코드 404를 반환한다.")
    @Test
    void 존재하지_않는_아이템을_준비중_상태로_변경_할_때_상태코드_404를_반환한다() throws Exception {
        doThrow(new ItemNotFoundException())
            .when(itemService)
            .changePrepare(any());

        mockMvc.perform(put(ITEM_DEFAULT_URL + "/{id}/prepare", 아이템_아이디))
            .andDo(print())
            .andExpect(status().isNotFound());
    }
}