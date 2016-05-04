import Stapes from 'stapes';

/**
 * @constructor
 * @param {string} title
 * @param {Array} result=[]
 * @param {Array} gamble=[]
 * @param {../util/FormatDate} dueDate
 * @param {boolean} joker
 * @param {number} score
 */
var FormEntry = Stapes.subclass({

    constructor : function ( title, result, gamble, dueDate, joker, score ) {
        
        this.title = title;
        this.result = result;
        this.gamble = gamble;
        this.dueDate = dueDate;
        this.joker = joker;
        this.score = score;
    },

    toJSON: function () {
        
        return {
            type: 'entry',
            title : this.title,
            result : this.result,
            gamble : this.gamble,
            dueDate : this.dueDate,
            joker : this.joker,
            score : this.score
        };
    }
});


export default FormEntry;