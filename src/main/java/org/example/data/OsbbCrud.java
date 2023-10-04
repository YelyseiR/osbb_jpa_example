package org.example.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OsbbCrud {

    private final EntityManager entityManager;

    public OsbbCrud(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<OwnerInfoResult> getOwnersWithoutCarPermissionAndLessThanTwoApartments() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OwnershipRights> query = cb.createQuery(OwnershipRights.class);
        Root<OwnershipRights> ownershipRightsRoot = query.from(OwnershipRights.class);
        Join<Residents, OwnershipRights> residentsJoin = ownershipRightsRoot.join("memberId");

        Predicate carPermissionPredicate = cb.equal(residentsJoin.get("carPermission"), 0);
        Subquery<Long> subquery = query.subquery(Long.class);
        Root<OwnershipRights> subqueryRoot = subquery.from(OwnershipRights.class);
        subquery.select(cb.count(subqueryRoot.get("id")));
        subquery.where(cb.equal(subqueryRoot.get("memberId").get("id"), residentsJoin.get("memberId").get("id")));
        Predicate lessThanTwoApartmentsPredicate = cb.lt(subquery, 2L);

        query.where(cb.and(carPermissionPredicate, lessThanTwoApartmentsPredicate));

        List<OwnershipRights> ownershipRights = entityManager.createQuery(query).getResultList();
        List<OwnerInfoResult> ownerInfoResults = new ArrayList<>();

        for (OwnershipRights ownershipRight : ownershipRights) {
            Residents residents = ownershipRight.getMemberId();
            OsbbMembers osbbMembers = ownershipRight.getMemberId().getMemberId();
            OwnerInfoResult ownerInfoResult = new OwnerInfoResult(
                    residents.getName(),
                    residents.getSurname(),
                    osbbMembers.getEmail(),
                    ownershipRight.getApartmentId().getBuildingId().getBuildingNumber(),
                    ownershipRight.getApartmentId().getApartmentNumber(),
                    ownershipRight.getApartmentId().getArea(),
                    ownershipRight.getApartmentId().getBuildingId().getAddress()
            );
            ownerInfoResults.add(ownerInfoResult);
        }
        return ownerInfoResults;
    }

    public void saveResultToFile(List<OwnerInfoResult> owners, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (OwnerInfoResult owner : owners) {
                String line = String.format(
                        "Ім'я: %s, Прізвище: %s, Email: %s, Номер будинку: %s, Номер квартири: %s, Площа квартири: %.2f, Адреса будинку: %s%n",
                        owner.name(),
                        owner.surname(),
                        owner.email(),
                        owner.buildingNumber(),
                        owner.apartmentNumber(),
                        owner.area(),
                        owner.address()
                );
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

