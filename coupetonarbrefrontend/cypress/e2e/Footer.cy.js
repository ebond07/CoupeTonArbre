describe('Footer', () => {
  beforeEach(() => {
    // Visit the home page before each test
    cy.visit('localhost:3000');
  });

  it('should display the footer on the home page with all information', () => {
    // Assert that the footer exists
    cy.get('[data-cy="footer"]').should('exist');

    // Assert the presence of the logo
    cy.get('[data-cy="CTALogo"]').should('exist');

    // Assert the presence of the "Website" section
    cy.get('[data-cy="linkHeaders"]').eq(0).should('contain.text', 'Website');
    cy.get('[data-cy="links"]').eq(0).should('have.attr', 'href', 'localhost:3000').should('contain.text', 'Home');
    cy.get('[data-cy="links"]').eq(1).should('have.attr', 'href', 'localhost:3000').should('contain.text', 'Our Work');
    cy.get('[data-cy="links"]').eq(2).should('have.attr', 'href', 'localhost:3000').should('contain.text', 'My Profile');
    cy.get('[data-cy="links"]').eq(3).should('have.attr', 'href', 'localhost:3000').should('contain.text', 'FR');

    // Assert the presence of the "Services We Offer" section
    cy.get('[data-cy="linkHeaders"]').eq(1).should('contain.text', 'Services We Offer');
    cy.get('[data-cy="info"]').eq(0).should('contain.text', 'Hedge, Shrub & Cone Trimming');
    cy.get('[data-cy="info"]').eq(1).should('contain.text', 'Hedge & Tree Pruning');

    // Assert the presence of the "Contact" section
    cy.get('[data-cy="linkHeaders"]').eq(2).should('contain.text', 'Contact');
    cy.get('[data-cy="links"]').eq(4).should('have.attr', 'href', 'https://maps.app.goo.gl/vkK4JbvrPjtQ4qeeA').should('contain.text', '7816 Champlain BLVD, Lasalle');
    cy.get('[data-cy="links"]').eq(5).should('have.attr', 'href', 'tel:4385046055').should('contain.text', '(438) 504-6055');

    // Assert the presence of the "Hours" section
    cy.get('[data-cy="linkHeaders"]').eq(3).should('contain.text', 'Hours');
    cy.get('[data-cy="info"]').eq(2).should('contain.text', 'Week: 8 A.M. - 8 P.M.');
    cy.get('[data-cy="info"]').eq(3).should('contain.text', 'Saturday: 9 A.M. - 6 P.M.');
    cy.get('[data-cy="info"]').eq(4).should('contain.text', 'Sunday: 9 A.M. - 5 P.M.');

    // Assert the presence of the horizontal rule
    cy.get('[data-cy="line"]').should('exist');

    // Assert the presence of the copyright information
    const currentYear = new Date().getFullYear();
    cy.get('[data-cy="copyright"]').should('contain.text', `@${currentYear} Coupe Ton Arbre. All rights reserved.`);
  });
});
