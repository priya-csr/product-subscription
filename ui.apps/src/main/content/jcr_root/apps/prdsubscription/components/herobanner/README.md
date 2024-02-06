#Hero Banner

herobanner component written in HTL.

Below core components are used to build hero banner using data-sly-resource

1. Image - Leverage Adobe Core Image
2. Title - Leverage Adobe Core Title
3. Rich Text Editor
4. Get an Estimate CTA 1 - Leverages Adobe Core Button while opening a pop-up modal on click.
5. Learn More CTA 2 - Leverage Adobe Core Button



##Features


Following properties of pop up modal can be configured

- Popup title
- Category Label of dropdown
- Product type Label of dropdown
- Text displayed at the bottom showing the estimate
- Allows configuration of CF to read product schema

### Enabling Get Estimate popup
Get estimate button should be configured with ID : <b>openModalBtn</b> in order to open pop up modal on click of get estimate. 
This ID is used in popup Java script.

### Use Object

The Accordion component uses the com.adobe.cq.wcm.core.components.models.ProductDetailsModel Sling model as its Use-object.

### Component Policy Configuration Properties

The following configuration properties are used and can be configured with Edit Dialog Properties :

    ./popuptitle
    ./categorylabel
    ./productlabel
    ./estimatetext
    ./fileReference


The component provides a prdsubscription.components.herobanner client library category that contains a recommended base CSS styling and JavaScript component. 

### BEM Description

``` BLOCK .cmp-herobnr ELEMENT cmp-accordion__item ELEMENT cmp-herobnr__title ELEMENT cmp-herobnr__cta-container  ELEMENT cmp-herobnr__image ELEMENT cmp-herobnr__cta-learnmore ELEMENT cmp-herobnr__modal ELEMENT cmp-herobnr__modal ELEMENT mp-herobnr__modal-content```


