package com.at.internship.lib.serialization;

import java.io.IOException;
import java.util.List;

public interface ISerializer<T> {
    void serialize(List<T> list) throws IOException;
    List<T> deserialize();
}
