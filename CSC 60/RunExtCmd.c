/* ----------------------------------------------------------------- */
/*                 RunExtCmd                            CLASS        */
/*------------------------------------------------------------------ */
// Elijah ******

#include "lab9_10.h"

void RunExtCmd(int argc, char **argv) 
{                             
    int ret;

    //call to the function Redirection
    Redirection(argc, argv);

    //call to execvp
    ret = execvp(argv[0], argv);
    
    if (ret == -1)    // error check for the exec call
    {                                       
        fprintf(stderr, "Error on the exec call\n");             
        _exit(EXIT_FAILURE);                                   
    }                                                         
                                                          
    return;
}
/* ----------------------------------------------------------------- */
