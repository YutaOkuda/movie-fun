package org.superbiz.moviefun.blobstore;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class FileStore implements BlobStore {

    @Override
    public void put(Blob blob) throws IOException {
        // ...

    }

    @Override
    public Optional<Blob> get(String name) throws IOException {
        // ...
        return Optional.ofNullable(null);
    }

    @Override
    public void deleteAll() {
        // ...
    }
}
