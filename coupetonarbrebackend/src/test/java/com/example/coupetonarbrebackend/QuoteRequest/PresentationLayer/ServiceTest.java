package com.example.coupetonarbrebackend.QuoteRequest.PresentationLayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceTest {

    @Test
    void shouldHaveCorrectNumberOfEnumValues() {
        assertEquals(14, Service.values().length);
    }

    @Test
    void shouldHaveExpectedEnumValues() {
        assertEquals(Service.HedgeTrimming, Service.valueOf("HedgeTrimming"));
        assertEquals(Service.TreeTrimming, Service.valueOf("TreeTrimming"));
        assertEquals(Service.TreeBranchRemoval, Service.valueOf("TreeBranchRemoval"));
        assertEquals(Service.SmallTreeRemoval, Service.valueOf("SmallTreeRemoval"));
        assertEquals(Service.HedgeTrimmingAndTreeTrimming, Service.valueOf("HedgeTrimmingAndTreeTrimming"));
        assertEquals(Service.HedgeTrimmingAndTreeBranchRemoval, Service.valueOf("HedgeTrimmingAndTreeBranchRemoval"));
        assertEquals(Service.TreeTrimmingAndTreeBranchRemoval, Service.valueOf("TreeTrimmingAndTreeBranchRemoval"));
        assertEquals(Service.HedgeTrimmingAndTreeTrimmingAndTreeBranchRemoval, Service.valueOf("HedgeTrimmingAndTreeTrimmingAndTreeBranchRemoval"));
        assertEquals(Service.HedgeTrimmingAndSmallTreeRemoval, Service.valueOf("HedgeTrimmingAndSmallTreeRemoval"));
        assertEquals(Service.TreeTrimmingAndSmallTreeRemoval, Service.valueOf("TreeTrimmingAndSmallTreeRemoval"));
        assertEquals(Service.HedgeTrimmingAndTreeTrimmingAndSmallTreeRemoval, Service.valueOf("HedgeTrimmingAndTreeTrimmingAndSmallTreeRemoval"));
        assertEquals(Service.HedgeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval, Service.valueOf("HedgeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval"));
        assertEquals(Service.TreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval, Service.valueOf("TreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval"));
        assertEquals(Service.HedgeTrimmingAndTreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval, Service.valueOf("HedgeTrimmingAndTreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval"));
    }

    @Test
    void shouldReturnCorrectStringRepresentation() {
        assertEquals("HedgeTrimming", Service.HedgeTrimming.toString());
        assertEquals("TreeTrimming", Service.TreeTrimming.toString());
        assertEquals("TreeBranchRemoval", Service.TreeBranchRemoval.toString());
        assertEquals("SmallTreeRemoval", Service.SmallTreeRemoval.toString());
        assertEquals("HedgeTrimmingAndTreeTrimming", Service.HedgeTrimmingAndTreeTrimming.toString());
        assertEquals("HedgeTrimmingAndTreeBranchRemoval", Service.HedgeTrimmingAndTreeBranchRemoval.toString());
        assertEquals("TreeTrimmingAndTreeBranchRemoval", Service.TreeTrimmingAndTreeBranchRemoval.toString());
        assertEquals("HedgeTrimmingAndTreeTrimmingAndTreeBranchRemoval", Service.HedgeTrimmingAndTreeTrimmingAndTreeBranchRemoval.toString());
        assertEquals("HedgeTrimmingAndSmallTreeRemoval", Service.HedgeTrimmingAndSmallTreeRemoval.toString());
        assertEquals("TreeTrimmingAndSmallTreeRemoval", Service.TreeTrimmingAndSmallTreeRemoval.toString());
        assertEquals("HedgeTrimmingAndTreeTrimmingAndSmallTreeRemoval", Service.HedgeTrimmingAndTreeTrimmingAndSmallTreeRemoval.toString());
        assertEquals("HedgeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval", Service.HedgeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval.toString());
        assertEquals("TreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval", Service.TreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval.toString());
        assertEquals("HedgeTrimmingAndTreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval", Service.HedgeTrimmingAndTreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval.toString());
    }

    @Test
    void shouldCompareEnumInstances() {
        assertTrue(Service.HedgeTrimming.compareTo(Service.TreeTrimming) < 0);
        assertTrue(Service.TreeTrimming.compareTo(Service.HedgeTrimming) > 0);
        assertEquals(0, Service.HedgeTrimming.compareTo(Service.HedgeTrimming));
    }
}
