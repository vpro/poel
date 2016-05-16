import Stapes from 'stapes';
import $ from 'jquery';

var CollapseController = Stapes.subclass( {

    constructor : function ( sections ) {
        this.$sections = $( sections );
        $.each( this.$sections, function ( i, section ) {

            this.bindClickHandlers( $( section ) );

        }.bind( this ) );

    },

    bindClickHandlers: function ( $section ) {

        var $sectionHeader = $section.find('.collapsible-section-header');
        var $sectionFooter = $section.find('.collapsible-section-footer');

        $.merge( $sectionFooter, $sectionHeader ).on( 'click', function () {

            $section.toggleClass('collapsible-section-opened');
            $section.toggleClass( $section.data( 'closedcolor' ) );
            $section.toggleClass( $section.data( 'opencolor' ) );

        }.bind( this ) );

    }

});

export default CollapseController;