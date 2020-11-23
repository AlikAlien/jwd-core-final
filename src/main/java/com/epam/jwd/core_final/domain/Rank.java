package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.exception.UnknownEntityException;

public enum Rank implements BaseEntity {
    TRAINEE(1L),
    SECOND_OFFICER(2L),
    FIRST_OFFICER(3L),
    CAPTAIN(4L);

    private final Long id;

    Rank(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * todo via java.lang.enum methods!
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * todo via java.lang.enum methods!
     *
     * @throws UnknownEntityException if such id does not exist
     */
    public static Rank getRankById(int id) {
        Rank rank;
        switch (id) {
            case 1:
                rank = Rank.TRAINEE;
                break;
            case 2:
                rank = Rank.SECOND_OFFICER;
                break;
            case 3:
                rank = Rank.FIRST_OFFICER;
                break;
            case 4:
                rank = Rank.CAPTAIN;
                break;
            default:
                throw new RuntimeException("Unknown index:" + id);
        }
        return rank;
    }
}
