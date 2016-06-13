import Stapes from 'stapes';
import $ from 'jquery';

var NavigationController = Stapes.subclass({


    constructor : function ( $navigation ) {

        var $navigationTitle = $navigation.find( '.top-navigation-title' );

        window.addEventListener( 'scroll' , function ( e ) {
            var distanceY = window.pageYOffset || document.documentElement.scrollTop;
            var shrinkOn = 65;

            if ( distanceY > shrinkOn ) {
                $navigation.addClass( 'smaller' );
            } else {
                if ( $navigation.hasClass( 'smaller' ) ) {
                    $navigation.removeClass( 'smaller' );
                }
            }
        });

        $navigationTitle.on( 'click' , function ( ) {
            $( 'html, body' ).animate( { scrollTop: '0' } , 500 );

        } );

    }

});

export default NavigationController;