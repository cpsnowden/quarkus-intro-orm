package org.cps.quarkus.jdbc;

import java.sql.SQLException;

public interface IArtistRepository {
    void persist(Artist artist) throws SQLException;

    Artist findById(Long id) throws SQLException;
}
