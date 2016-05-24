import Stapes from 'stapes';
import $ from 'jquery';

import HandlebarsRuntime from 'handlebars-runtime';
import userGroupTemplate from '../views/user-group.hbs!';

var UserGroupController = Stapes.subclass({

    /**
     * @param {HTMLElement} form
     */
    constructor : function ( form, overlay ) {

        this.$form = $( form );
        this.$userGroupsContainer = this.$form.find( '.user-groups' );
        this.$formSubmit = this.$form.find( 'button[type=submit]' );
        this.$formReset = this.$form.find( 'button[type=reset]' );
        this.$formAdd = this.$form.find( '.add-user-group' );

        this.$alertOverlay = $( overlay );
        this.$alertOverlayButton = this.$alertOverlay.find( '.alert-overlay__close-button' );

        this.initialFormState = this.$form.serialize();

        this.bindHandlers();

    },

    addUserGroup: function () {

        this.$userGroupsContainer.append(

            userGroupTemplate({
                index: $( '.user-group' ).length
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


        this.$form.on( 'click', '.delete-user-group', function ( e ) {

            var c = confirm( 'Groep verwijderen?' );
            if ( c == true ) {
                var userGroup = $( e.currentTarget ).parent();
                this.deleteUserGroup( userGroup );
                this.resetIds();
            }

        }.bind( this ) );

    },

    deleteUserGroup: function ( userGroup ) {

        userGroup.remove();

    },

    resetIds: function () {

        this.$form.find( 'input[name*="userGroups"]').each( function ( i ) {
            var value = 'userGroups[' + i + '].name';
            this.name = value;
        } );

    }

    // TODO: implement this for userGroups as well
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

export default UserGroupController;