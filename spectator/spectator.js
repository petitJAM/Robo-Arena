/*
 * Spectator.js
 *
 */

$(function() {

    // do stuff
    var gamesRootRef = new Firebase("http://robo-arena.firebaseIO.com/games");

    gamesRootRef.on('child_added', function(snap) {
        if (snap.val() === null) {
            alert("nothing here");
        } else {
            console.log("Found a game " + snap.name());

            if (snap.child('info').child('allow_spectators').val()) {
                var li = $("<li>").append(snap.name());
                li.click(function() {
                    spectate_game(snap.name());
                });
                $("#game-select-dd").append(li)
                console.log("new game added to dropdown");
                
            } else { 
                console.log("not added :( " + snap.child('info').child('allow_spectators').val());
            }
        }
    });

    window.requestAnimFrame = (function(callback) {
        return window.requestAnimationFrame || window.webkitRequestAnimationFrame || 
            window.mozRequestAnimationFrame || window.oRequestAnimationFrame || window.msRequestAnimationFrame ||
            function(callback) {
                window.setTimeout(callback, 1000 / 60);
            };
    })();

    function drawCircle(glove_circle, context) {
        context.beginPath();
        context.arc(glove_circle.x, glove_circle.y, glove_circle.radius, 0, 2*Math.PI);
        context.fillStyle = glove_circle.color;
        context.fill();
        context.lineWidth = glove_circle.borderWidth;
        context.strokeStyle = 'black';
        context.stroke();
    }

    function re_draw_everything(context) {
        context.clearRect(0, 0, canvas.width, canvas.height);

        // Draw gloves
        drawCircle(p1_L, context);
        drawCircle(p1_R, context);
        drawCircle(p2_L, context);
        drawCircle(p2_R, context);

        // request new frame
        requestAnimFrame(function() {
          re_draw_everything(context);
        });
    }

    /********************* LEFT ****************************/

    function p1_left_jab() {

        var n_frames = 8,
            x_speed  = 30;

        p1_L.x = p1_L.start_x;
        p1_L.y = p1_L.start_y;
        p1_L.radius = p1_L.start_radius;

        function p1_left_jab_h(frame) {
            p1_L.x += x_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p1_left_jab_h(frame + 1) }, 50);
            }
        }

        p1_left_jab_h(1);
    }

    function p2_left_jab() {

        var n_frames = 8,
            x_speed  = -30;

        p2_L.x = p2_L.start_x;
        p2_L.y = p2_L.start_y;

        function p2_left_jab_h(frame) {
            p2_L.x += x_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p2_left_jab_h(frame + 1) }, 50);
            }
        }

        p2_left_jab_h(1);
    }

    function p1_left_hook() {

        var n_frames = 8,
            x_speed  = 30,
            y_speed  = 10;

        p1_L.x = p1_L.start_x;
        p1_L.y = p1_L.start_y;
        p1_L.radius = p1_L.start_radius;

        function p1_left_hook_h(frame) {
            p1_L.x += x_speed * ((frame > n_frames/2) ? -1 : 1); // reversed

            var direction;
            if (frame > 3*n_frames/4) {
                direction = 1;
            } else if (frame > n_frames/2) {
                direction = -1;
            } else if (frame > n_frames/4) {
                direction = 1;
            } else {
                direction = -1;
            }

            p1_L.y += y_speed * direction

            if (frame < n_frames) {
                setTimeout(function() { p1_left_hook_h(frame + 1) }, 50);
            }
        }

        p1_left_hook_h(1);
    }

    function p2_left_hook() {

        var n_frames = 8,
            x_speed  = -30,
            y_speed  = -10;

        p2_L.x = p2_L.start_x;
        p2_L.y = p2_L.start_y;

        function p2_left_hook_h(frame) {
            p2_L.x += x_speed * ((frame > n_frames/2) ? -1 : 1);

            var direction;
            if (frame > 3*n_frames/4) {
                direction = 1;
            } else if (frame > n_frames/2) {
                direction = -1;
            } else if (frame > n_frames/4) {
                direction = 1;
            } else {
                direction = -1;
            }

            p2_L.y += y_speed * direction

            if (frame < n_frames) {
                setTimeout(function() { p2_left_hook_h(frame + 1) }, 50);
            }
        }

        p2_left_hook_h(1);
    }

    function p1_left_uppercut() {

        var n_frames = 8,
            x_speed  = 30,
            radius_speed = 10;

        p1_L.x = p1_L.start_x;
        p1_L.y = p1_L.start_y;
        p1_L.radius = p1_L.start_radius;

        function p1_left_uppercut_h(frame) {
            p1_L.x += x_speed * ((frame > n_frames/2) ? -1 : 1);
            p1_L.radius += radius_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p1_left_uppercut_h(frame + 1) }, 50);
            }
        }

        p1_left_uppercut_h(1);
    }

    function p2_left_uppercut() {

        var n_frames = 8,
            x_speed  = -30,
            radius_speed = 10;

        p2_L.x = p2_L.start_x;
        p2_L.y = p2_L.start_y;
        p2_L.radius = p2_L.start_radius;

        function p2_left_uppercut_h(frame) {
            p2_L.x += x_speed * ((frame > n_frames/2) ? -1 : 1);
            p2_L.radius += radius_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p2_left_uppercut_h(frame + 1) }, 50);
            }
        }

        p2_left_uppercut_h(1);
    }

    /********************** RIGHT **************************/

    function p1_right_jab() {

        var n_frames = 8,
            x_speed  = 30;

        p1_R.x = p1_R.start_x;
        p1_R.y = p1_R.start_y;

        function p1_right_jab_h(frame) {
            p1_R.x += x_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p1_right_jab_h(frame + 1) }, 50);
            }
        }

        p1_right_jab_h(1);
    }

    function p2_right_jab() {

        var n_frames = 8,
            x_speed  = -30;

        p2_R.x = p2_R.start_x;
        p2_R.y = p2_R.start_y;

        function p2_right_jab_h(frame) {
            p2_R.x += x_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p2_right_jab_h(frame + 1) }, 50);
            }
        }

        p2_right_jab_h(1);
    }

    function p1_right_hook() {

        var n_frames = 8,
            x_speed  = 30,
            y_speed  = -10;

        p1_R.x = p1_R.start_x;
        p1_R.y = p1_R.start_y;

        function p1_right_hook_h(frame) {
            p1_R.x += x_speed * ((frame > n_frames/2) ? -1 : 1); // reversed

            var direction;
            if (frame > 3 * n_frames/4) {
                direction = 1;
            } else if (frame > n_frames/2) {
                direction = -1;
            } else if (frame > n_frames/4) {
                direction = 1;
            } else {
                direction = -1;
            }

            p1_R.y += y_speed * direction

            if (frame < n_frames) {
                setTimeout(function() { p1_right_hook_h(frame + 1) }, 50);
            }
        }

        p1_right_hook_h(1);
    }

    function p2_right_hook() {

        var n_frames = 8,
            x_speed  = -30,
            y_speed  = 10;

        p2_R.x = p2_R.start_x;
        p2_R.y = p2_R.start_y;

        function p2_right_hook_h(frame) {
            p2_R.x += x_speed * ((frame > n_frames/2) ? -1 : 1);

            var direction;
            if (frame > 3*n_frames/4) {
                direction = 1;
            } else if (frame > n_frames/2) {
                direction = -1;
            } else if (frame > n_frames/4) {
                direction = 1;
            } else {
                direction = -1;
            }

            p2_R.y += y_speed * direction

            if (frame < n_frames) {
                setTimeout(function() { p2_right_hook_h(frame + 1) }, 50);
            }
        }

        p2_right_hook_h(1);
    }

    function p1_right_uppercut() {

        var n_frames = 8,
            x_speed  = 30,
            radius_speed = 10;

        p1_R.x = p1_R.start_x;
        p1_R.y = p1_R.start_y;
        p1_R.radius = p1_R.start_radius;

        function p1_right_uppercut_h(frame) {
            p1_R.x += x_speed * ((frame > n_frames/2) ? -1 : 1);
            p1_R.radius += radius_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p1_right_uppercut_h(frame + 1) }, 50);
            }
        }

        p1_right_uppercut_h(1);
    }

    function p2_right_uppercut() {

        var n_frames = 8,
            x_speed  = -30,
            radius_speed = 10;

        p2_R.x = p2_R.start_x;
        p2_R.y = p2_R.start_y;
        p2_R.radius = p2_R.start_radius;

        function p2_right_uppercut_h(frame) {
            p2_R.x += x_speed * ((frame > n_frames/2) ? -1 : 1);
            p2_R.radius += radius_speed * ((frame > n_frames/2) ? -1 : 1);

            if (frame < n_frames) {
                setTimeout(function() { p2_right_uppercut_h(frame + 1) }, 50);
            }
        }

        p2_right_uppercut_h(1);
    }

    /************************************************/

    var canvas = document.getElementById('game-canvas');
    var context = canvas.getContext('2d');

    var p1_L = {
        x: 250,
        y: 100,
        start_x: 250,
        start_y: 100,
        color: '#3333FF',
        radius: 75,
        start_radius: 75,
        borderWidth: 3
    };

    var p1_R = {
        x: 250,
        y: 300,
        start_x: 250,
        start_y: 300,
        color: '#3333FF',
        radius: 75,
        start_radius: 75,
        borderWidth: 3
    };

    var p2_R = {
        x: 700,
        y: 100,
        start_x: 700,
        start_y: 100,
        color: '#FF3333',
        radius: 75,
        start_radius: 75,
        borderWidth: 3
    };

    var p2_L = {
        x: 700,
        y: 300,
        start_x: 700,
        start_y: 300,
        color: '#FF3333',
        radius: 75,
        start_radius: 75,
        borderWidth: 3
    };

    function start_anim() {
        var startTime = (new Date()).getTime();
        re_draw_everything(context);
    }

    /** P1 **/

    $("#p1_left_jab_btn").click(p1_left_jab);
    $("#p1_left_hook_btn").click(p1_left_hook);
    $("#p1_left_uppercut_btn").click(p1_left_uppercut);

    $("#p1_right_jab_btn").click(p1_right_jab);
    $("#p1_right_hook_btn").click(p1_right_hook);
    $("#p1_right_uppercut_btn").click(p1_right_uppercut);

    /** P2 **/

    $("#p2_left_jab_btn").click(p2_left_jab);
    $("#p2_left_hook_btn").click(p2_left_hook);
    $("#p2_left_uppercut_btn").click(p2_left_uppercut);

    $("#p2_right_jab_btn").click(p2_right_jab);
    $("#p2_right_hook_btn").click(p2_right_hook);
    $("#p2_right_uppercut_btn").click(p2_right_uppercut);
   


    // TODO: Add listeners for FB

    var gameRef = null;

    function spectate_game(name) {
        console.log("Start spectating " + name);
        $("#game-spectate-view").css("visibility", "");
        $("#title").html("Spectating " + name);

        gameRef = gamesRootRef.child(name);
        p1Ref = gameRef.child('player_creator');
        p2Ref = gameRef.child('player_joiner');

        /** P1 **/
        p1Ref.child('left_jab').on(      'value', function() { p1_left_jab();        });
        p1Ref.child('left_hook').on(     'value', function() { p1_left_hook();       });
        p1Ref.child('left_uppercut').on( 'value', function() { p1_left_uppercut();   });

        p1Ref.child('right_jab').on(     'value', function() { p1_right_jab();       });
        p1Ref.child('right_hook').on(    'value', function() { p1_right_hook();      });
        p1Ref.child('right_uppercut').on('value', function() { p1_right_uppercut();  });


        /** P2 **/
        p2Ref.child('left_jab').on(      'value', function() { p2_left_jab();        });
        p2Ref.child('left_hook').on(     'value', function() { p2_left_hook();       });
        p2Ref.child('left_uppercut').on( 'value', function() { p2_left_uppercut();   });

        p2Ref.child('right_jab').on(     'value', function() { p2_right_jab();       });
        p2Ref.child('right_hook').on(    'value', function() { p2_right_hook();      });
        p2Ref.child('right_uppercut').on('value', function() { p2_right_uppercut();  });

        setTimeout(function() {
            start_anim();
            reset();
        }, 500);
    }

    function reset() {
        p1_L.x = p1_L.start_x;
        p1_L.y = p1_L.start_y;

        p1_R.x = p1_R.start_x;
        p1_R.y = p1_R.start_y;

        p2_L.x = p2_L.start_x;
        p2_L.y = p2_L.start_y;

        p2_R.x = p2_R.start_x;
        p2_R.y = p2_R.start_y;

        re_draw_everything();
    }

});