package tool

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.gmail.kamil.jarmusik.DataRepoGithub.repo.info.domain.InfoRepo

class JsonDeserializer {

    static InfoRepo deserialize(ObjectMapper objectMapper, String jsonString) {
        return objectMapper.readValue(jsonString, new TypeReference<InfoRepo>(){})
    }
}
