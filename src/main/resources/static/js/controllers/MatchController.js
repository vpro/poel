import Stapes from 'stapes';
import $ from 'jquery';

import HandlebarsRuntime from 'handlebars-runtime';
import matchTemplate from '../views/match.hbs!';

var MatchController = Stapes.subclass({

    constructor : function ( $form, $overlay ) {

        this.$form = $form;
        this.$userGroupsContainer = this.$form.find( '.matches' );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );
        this.$formAdd = this.$form.find( '.add-match' );
        this.$formSort = this.$form.find( '.sort-matches' );

        this.$alertOverlay = $overlay;
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );


        this.sortMatches();

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();

    },

    addUserGroup: function () {

        this.$userGroupsContainer.append(

            matchTemplate({
                index: $( '.match' ).length
            })
        );
    },

    bindHandlers: function () {

        this.$formReset.on( 'click', function () {
            setTimeout( function () {
                this.checkFormChanges();
            }.bind( this ), 1 );
        }.bind( this ) );


        this.$formAdd.on( 'click', function () {
            this.addUserGroup();
        }.bind( this ) );


        this.$alertOverlayButton.on( 'click', function () {
            this.$alertOverlay.addClass( 'hidden' );
        }.bind( this ) );


        this.$form.on( 'click', '.delete-match', function ( e ) {

            var c = confirm( 'Wedstrijd verwijderen?' );
            if ( c == true ) {
                var match = $( e.currentTarget ).parent();
                this.deleteUserGroup( match );
            }

        }.bind( this ) );

        this.$formSort.on( 'click', function () {
            this.sortMatches();
        }.bind( this ) );

    },

    deleteUserGroup: function ( match ) {

        match.remove();

    },

    sortMatches: function () {

        // get all matches
        var matches = this.$userGroupsContainer.find( '.match' );

        // create an array with objects containing match + date
        var matchObjects = matches.map( function ( index, match, array ) {

            var date = $( match ).find( 'input[type=datetime-local]' ).val();

            return {
                date: date,
                match: match
            };
        });

        // sort matchObjects by date
        matchObjects.sort( function ( a, b ) {
            a = new Date( a.date );
            b = new Date( b.date );

            return a < b ? -1 : a > b ? 1 : 0;
        });

        // remove original matches from the DOM
        this.$userGroupsContainer.empty();

        // inject the ordered matchObjects
        matchObjects.each( function ( i, match ) {
            this.$userGroupsContainer.append( match.match );
        }.bind( this ) );

    }


    // TODO: implement this for matches as well
    // disabled submit button if nothing has changed or if the form validation fails

    //    /**
    //     *
    //     * Check if the form has changed since page load
    //     *
    //     */
    //
    //    checkFormChanges: function () {
    //        if( this.hasFormChangedSincePageLoad() ) {
    //            this.enableSubmit();
    //        } else {
    //            this.disableSubmit();
    //        }
    //    },
    //
    //    enableSubmit: function () {
    //        this.$formSubmit.prop( 'disabled', false );
    //    },
    //
    //    disableSubmit: function () {
    //        this.$formSubmit.prop( 'disabled', true );
    //    },
    //
    //    hasFormChangedSincePageLoad: function () {
    //
    //        return this.$form.serialize() !== this.initialFormState;
    //    }

});

export default MatchController;