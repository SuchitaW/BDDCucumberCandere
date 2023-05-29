@healthCheck
Feature: E-Commerce Jewellery website healthcheck

  Background: Navigation to the URL
    Given User navigate to URL and open the landing page

  @URLRedirection
  Scenario: User naviaget to URL, User redirect to landing page with expected current URL
    When User is on landing page
    Then Validate current URL of landing page with expected URL

  @LandingPageTitle
  Scenario: User naviaget to URL, User redirect to landing page with expected page title
    When User is on landing page
    Then Validate title of landing page with expected title as "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

  @DisplayLogo
  Scenario: User able to see logo of application on landing page
    When User is on landing page
    Then User see the logo of application

  @LogoHeight
  Scenario: Logo present on landing page with specific height dimension
    When fetch the height of logo
    Then Height of logo should be "53"

  @LogoWidth
  Scenario: Logo present on landing page with specific width dimension
    When fetch the width of logo
    Then Width of logo should be "240"

  @ProductCategory
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Trending            |
      | Rings               |
      | Earrings            |
      | Pendants            |
      | Necklace            |
      | Bangles & Bracelets |
      | Mangalsutra         |
      | Chains              |
      | Solitaires          |
      | Other Jewellery     |
      | Gifts               |
      | Offers              |
    And Size of product category should be 12
    
    @Searchprod1
  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    When User navigated to the home application url
    And User Search for product on search bar "ring"
    Then Search Result page is displayed with title contains " 'ring'"
