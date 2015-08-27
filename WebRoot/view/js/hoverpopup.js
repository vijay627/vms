$(document).ready(function() {
   
    $(function () {
        $('.bubbleInfo').each(function () {
            var distance = 10;
            var time = 250;
            var hideDelay = 0;
            var hideDelayTimer = null;
            var beingShown = false;
            var shown = false;
            var trigger = $('.trigger', this);
            var info = $('.popup', this).css('opacity', 0);
    
            $([trigger.get(0), info.get(0)]).mouseover(function () {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                if (beingShown || shown) {
                    // don't trigger the animation again
                    return;
                } else {
                    // reset position of info box
                    beingShown = true;
    
                    info.css({
                        top: 0,
                        left: 0,
                        display: 'block'
                    }).animate({
                        top: '-=' + distance + 'px',
                        opacity: 1
                    }, time, 'swing', function() {
                        beingShown = false;
                        shown = true;
                    });
                }
    
                return false;
            }).mouseout(function () {
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                hideDelayTimer = setTimeout(function () {
                    hideDelayTimer = null;
                    info.animate({
                        top: '-=' + distance + 'px',
                        opacity: 0
                    }, time, 'swing', function () {
                        shown = false;
                        info.css('display', 'none');
                    });
    
                }, hideDelay);
    
                return false;
            });
        });
    });
	
    $(function () {
        
        $('.searchpopup').each(function () {

            var distance = 10;
            var time = 250;
            var hideDelay = 0;
            var hideDelayTimer = null;
            var beingShown = false;
            var shown = false;
            var trigger = $('.trigger', this);
            var focus = false;
            
            var info = $('#dpop', this).css('opacity', 0);

            $([trigger.get(0), info.get(0)]).mouseover(function () {
                
                // Because .trigger is not visible on the first mouseover, this only applies to .popup
                // $("input:visible:enabled:first").focus();
                
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                if (beingShown || shown) {
                    // don't trigger the animation again
                    return;
                } else {
                    // reset position of info box
                    beingShown = true;
                    info.css({
                        top: 0,
                        left: 0,
                        display: 'block'
                    }).animate({
                        top: '-=' + distance + 'px',
                        opacity: 1
                    }, time, 'swing', function() {
                        beingShown = false;
                        shown = true;
                    });
                }

                return false;
            });

            // Check to see if search box is focused, if so set flag and unbind mouseleave action
            $('#searchpopup input').focus(function () {
                focus = true;
                $('#dpop').unbind("mouseleave");
            });
            
            // If search box is not focused, bind event to mouseleave event to hide search box
            if (!focus) {

                $('#dpop').bind("mouseleave",function() {                            
                    info.animate({ top: '-=' + distance + 'px', opacity: 0 }, time, 'swing', function () {
                        shown = false;
                        info.css('display', 'none');
                    });
                });
            }
            
            // If we click outside the search box and lose focus..
            $('#searchpopup input[type="text"]').blur(function() {
                
                // Rebind action to hide search box on mouseleave event
                $('#dpop').bind("mouseleave",function() {
                    info.animate({ top: '-=' + distance + 'px', opacity: 0 }, time, 'swing', function () {
                        shown = false;
                        info.css('display', 'none');
                    });
                });
                
                if (hideDelayTimer) clearTimeout(hideDelayTimer);
                
                hideDelayTimer = setTimeout(function () {
                    hideDelayTimer = null;
                    info.animate({ top: '-=' + distance + 'px', opacity: 0 }, time, 'swing', function () {
                        shown = false;
                        info.css('display', 'none');
                    });
            
                }, hideDelay);
            
                return false;                
            });
        });
    });
});