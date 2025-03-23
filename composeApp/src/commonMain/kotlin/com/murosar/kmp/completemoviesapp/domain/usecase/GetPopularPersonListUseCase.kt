package com.murosar.kmp.completemoviesapp.domain.usecase

import com.murosar.kmp.completemoviesapp.domain.model.PopularPerson
import com.murosar.kmp.completemoviesapp.domain.repository.PersonRepository
import com.murosar.kmp.completemoviesapp.domain.utils.CoroutineResult

fun interface GetPopularPersonListUseCase {
    suspend operator fun invoke(): CoroutineResult<List<PopularPerson>>
}

class GetPopularPersonListUseCaseImpl(
    private val personRepository: PersonRepository,
) : GetPopularPersonListUseCase {
    override suspend operator fun invoke(): CoroutineResult<List<PopularPerson>> = personRepository.getPopularPersonList()
}
