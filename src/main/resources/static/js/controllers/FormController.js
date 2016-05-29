import Stapes from 'stapes';
import $ from 'jquery';

const MAX_MULTIPLIERS = 5;

var FormController = Stapes.subclass({

    constructor : function ( $form, $overlay ) {

        this.$form = $form;
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );

        this.$matchPredictions = this.$form.find( '.match-prediction' );
        this.$predictionInputs = this.$form.find( 'input.prediction' );
        this.$multiplierInputs = this.$form.find( 'input[name$="multiplier"]' );

        this.$allMultipliers = $( 'input[name$="multiplier"]' );

        this.$alertOverlay = $overlay;
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.$navigationSubTitle = $('.top-navigation-subtitle');
        this.$navigationSubTitle.data('original-value', this.$navigationSubTitle.html() );

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();
        this.handleMultiplierState();
    },

    bindHandlers: function () {

        this.$predictionInputs.each( function ( i, el ) {

            $( el ).on( 'keyup mouseup', function () {
                this.checkFormChanges();
                this.validateMatchPredictions();
            }.bind( this ) );

            el.addEventListener( 'mousewheel', function () {
                this.checkFormChanges();
                this.validateMatchPredictions();
            }.bind( this ) );

        }.bind( this ) );

        this.$multiplierInputs.on( 'change', function () {

            this.handleMultiplierState();

            setTimeout( function () {

                this.checkFormChanges();

            }.bind( this ), 1 );
        }.bind( this ) );

        this.$formReset.on( 'click', function () {

            setTimeout( function () {

                this.checkFormChanges();

                this.validateMatchPredictions();

            }.bind( this ), 1 );

        }.bind( this ) );

        this.$alertOverlayButton.on( 'click', function () {

            this.$alertOverlay.addClass( 'hidden' );

        }.bind( this ) );

        this.$form.on( 'submit', this.handleSubmit.bind( this ) );

    },

    handleMultiplierState: function () {

        var multipliersChecked = this.$allMultipliers.filter(':checked').length;
        var multipliersLeft = Math.max( 0, MAX_MULTIPLIERS - multipliersChecked );

        if ( multipliersChecked >= MAX_MULTIPLIERS ) {
            this.$multiplierInputs.filter(':not(:checked)').prop('disabled', 'disabled');
        } else {
            this.$multiplierInputs.filter(':disabled').prop('disabled', null );
        }

        this.$navigationSubTitle.html( this.$navigationSubTitle.data('original-value') +
            ' ( '+ multipliersLeft +' joker'+ ( ( multipliersLeft == 1 ) ? '' : 's' ) +' over )' );
    },

    handleSubmit: function ( e ) {
        if ( this.$allMultipliers.filter(':checked').length > MAX_MULTIPLIERS ) {

            if ( ! confirm('Je hebt meer dan 5 jokers ingezet. Als je nu opslaat gaan je '+
                    'zojuist ingevulde voorspellingen verloren. Wil je daarmee doorgaan?') ) {
                e.preventDefault();
                e.stopPropagation();
            }
        }
    },

    validateMatchPredictions: function () {

        this.$matchPredictions.each( function ( i, matchPrediction ) {

            var $matchPrediction = $( matchPrediction );

            var homePrediction = $matchPrediction.find( '.home-prediction' ).val();
            var awayPrediction = $matchPrediction.find( '.away-prediction' ).val();

            if( homePrediction.length < 1 && awayPrediction.length < 1 ) {

                // both fields are empty so there's no need to validate
                $matchPrediction.removeClass( 'not-valid' );

            } else {

                if(
                    ( homePrediction.length > 0 && awayPrediction.length > 0 )
                    && isNumeric( homePrediction )
                    && isNumeric( awayPrediction )
                    && homePrediction > -1
                    && awayPrediction > -1
                    && ( ( homePrediction.length > 1 && homePrediction.indexOf( 0 ) === 0 ) !== true )
                    && ( ( awayPrediction.length > 1 && awayPrediction.indexOf( 0 ) === 0 ) !== true )
                ) {
                    // All is well
                    $matchPrediction.removeClass( 'not-valid' );
                } else {
                    $matchPrediction.addClass( 'not-valid' );
                    this.disableSubmit();
                }

            }

        }.bind( this ) );

    },

    /**
     *
     * Check if the form has changed since page load
     *
     */

    checkFormChanges: function () {
        if( this.hasFormChangedSincePageLoad() ) {
            this.enableSubmit();
        } else {
            this.disableSubmit();
        }
    },

    enableSubmit: function () {
        this.$formSubmit.prop( 'disabled', false );
    },

    disableSubmit: function () {
        this.$formSubmit.prop( 'disabled', true );
    },

    hasFormChangedSincePageLoad: function () {

        return this.$form.serialize() !== this.initialFormState;
    }
});

function isNumeric ( n ) {
    return ! isNaN( parseFloat( n ) ) && isFinite( n );
}

export default FormController;