package org.example.carrier.global.feign.nextcloud.dto.response;

public record ShareUrlResponse(
        OcsElement ocs
) {
    public String getUrl() {
        return ocs.data.url;
    }

    private record OcsElement(
            DataElement data
    ) {
    }

    private record DataElement(
            String url
    ) {
    }
}
