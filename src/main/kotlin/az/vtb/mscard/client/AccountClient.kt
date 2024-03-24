package az.vtb.mscard.client

import az.vtb.mscard.model.CreateAccountRequestDto
import az.vtb.mscard.model.CreateAccountResponseDto
import az.vtb.mscard.model.GetUserAccountResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(
    name = "account-service",
    url = "\${client.urls.ms-account}"
)
interface AccountClient {
    @PostMapping
    fun createAccount(@RequestBody createAccountRequest: CreateAccountRequestDto): CreateAccountResponseDto

    @GetMapping("/card/{cardId}")
    fun getAccountByCardId(@PathVariable cardId: String): GetUserAccountResponseDto

    @DeleteMapping("/{accountId}")
    fun deleteAccount(@PathVariable accountId: String)
}
