[#-- These constants are referenced outside this macro --]
[#assign FULL_WIDTH = 'full']
[#assign FULL_WIDTH_PADDED = 'full-padded']
[#assign PADDED = 'padded']
[#assign TWO_COLUMNS = '58']
[#assign THREE_COLUMNS = '83']
[#assign FOUR_COLUMNS = '100']

[#assign ONE_THIRD = '33']
[#assign TWO_THIRD = '67']
[#assign FIFTY = '50']
[#assign HUNDRED = '100']

[#macro sectionWithLayout content title='' addCss='' addContainerCss='' titleFollowsTheme=false collapsible=false backGroundColor=""]

    [#local layout = content.layout ! TWO_COLUMNS]

<section class="section-with-layout ${ addCss } [#if collapsible]collapsible-section[/#if]">

    [#if collapsible]
        <div class="collapsible-section-header"></div>
    <div class="collapsible-section-body [#if backGroundColor?has_content]theme-border-color[#else]primary-border-color[/#if]">
    [/#if]

    [#if layout == FULL_WIDTH ]
    <div class="section-with-layout-content-stretch ${ addContainerCss }">
    [/#if]

    [#if layout == FULL_WIDTH_PADDED ]
    <div class="section-with-layout-content-stretch section-with-layout-content-padded ${ addContainerCss }">
    [/#if]

    [#if layout == TWO_COLUMNS ]
    <div class="grid">
        [#if ! collapsible]<div class="col col-12-2"></div>[/#if]
    <div class="col col-12-7">
    <div class="section-with-layout-content ${ addContainerCss }">
    [/#if]

    [#if layout == THREE_COLUMNS ]
    <div class="grid">
        [#if ! collapsible]<div class="col col-12-2"></div>[/#if]
    <div class="col col-12-10">
    <div class="section-with-layout-content ${ addContainerCss }">
    [/#if]

    [#if layout == FOUR_COLUMNS ]
    <div class="grid">
    <div class="section-with-layout-content ${ addContainerCss }">
    [/#if]

    [#if title ? has_content ]
        <div class="col-gutter">
            <h2 class="section-with-layout-title h5 [#if titleFollowsTheme] theme-title [/#if][#if collapsible] collapsible-section-with-layout-title [#if layout == PADDED || layout == FULL_WIDTH ] full-width-collapsible-section-with-layout-title[/#if][/#if]">${ title! }</h2>
        </div>
    [/#if]

[#-- The Layouted Section Content  --]
    [#nested]

    [#if layout == FOUR_COLUMNS ]
    </div>
    </div>
    [/#if]

    [#if layout == THREE_COLUMNS ]
    </div>
    </div>
    </div>
    [/#if]

    [#if layout == TWO_COLUMNS ]
    </div>
    </div>
    </div>
    [/#if]

    [#if layout == FULL_WIDTH_PADDED ]
    </div>
    [/#if]

    [#if layout == FULL_WIDTH ]
    </div>
    [/#if]

    [#if collapsible]
    </div><!-- /collapsible-section-body -->
        <div class="collapsible-section-footer">
            <div class="collapsible-section-footer-lip [#if backGroundColor?has_content]theme-border-color bg-${backGroundColor}[#else]primary-border-color no-bg[/#if]"></div>
            <i class="glyph glyph-down theme-icon"></i>
        </div>
    [/#if]

</section>

[/#macro]

[#-- return the width of a component as a css class --]
[#function widthClass width ]

    [#switch width ! HUNDRED ]

        [#case TWO_THIRD ]
            [#local css = 'col col-3-2' ]
            [#break]

        [#case FIFTY ]
            [#local css = 'col col-2-1' ]
            [#break]

        [#case ONE_THIRD ]
            [#local css = 'col col-3-1' ]
            [#break]

        [#default ]
            [#local css = 'col' ]
            [#break]

    [/#switch]

    [#return css ]

[/#function]