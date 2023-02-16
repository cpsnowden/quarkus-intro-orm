package org.cps.quarkus.jdbc;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Random;

/**
 * This repository uses raw JDBC to persist and retrieve the artist pojo
 */
//Singleton
@ApplicationScoped
public class ArtistRepository implements IArtistRepository {

    private final Random idGenerator = new Random();

    @Inject
    DataSource dataSource;

    @Override
    public void persist(Artist artist) throws SQLException {

        String sql = "INSERT INTO t_artists (id, name, bio, created_date) VALUES (?, ?, ?, ?)";

        artist.setId(Math.abs(idGenerator.nextLong()));

        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, artist.getId());
            ps.setString(2, artist.getName());
            ps.setString(3, artist.getBio());
            ps.setTimestamp(4, Timestamp.from(artist.getCreatedDate()));
            ps.executeUpdate();
        }
    }

    @Override
    public Artist findById(Long id) throws SQLException {

        String sql = "SELECT id, name, bio, created_date FROM t_artists WHERE id = ?";

        Artist artist = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    artist = new Artist();
                    artist.setId(rs.getLong(1));
                    artist.setName(rs.getString(2));
                    artist.setBio(rs.getString(3));
                    artist.setCreatedDate(rs.getTimestamp(4).toInstant());
                }
            }

        }
        return artist;
    }
}
