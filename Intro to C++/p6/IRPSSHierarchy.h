// name: Weiqian Zhi
// Wisc ID: wzhi3

#ifndef CS368_A6_IRPSSHIERARCHY_H
#define CS368_A6_IRPSSHIERARCHY_H

template<typename T>
class IRPSSHierarchy {
    T member;
    IRPSSHierarchy<T> *left;
    IRPSSHierarchy<T> *right;

public:
    IRPSSHierarchy() {}

    IRPSSHierarchy(T _member) {
        this->member = _member;
        this->left = nullptr;
        this->right = nullptr;
    }

    void Insert(T new_member) {
        try {
            if (new_member < member) {
                if (this->right == nullptr) {
                    right = new IRPSSHierarchy(new_member);
                }
                else {right->Insert(new_member);}
            }
            else {
                if (this->left == nullptr) {
                    left = new IRPSSHierarchy(new_member);
                }
                else {left->Insert(new_member);}
            }
        }
        catch (Punches *p) {
            cout << p->what() << endl;
            if ((p->challenger) > (p->defender)) {member = new_member;}
        }

    }

    friend ostream &operator<<(ostream &out, const IRPSSHierarchy &hierarchy) {
        if (hierarchy.left != nullptr) {cout << *hierarchy.left;}

        cout << hierarchy.member;

        if (hierarchy.right != nullptr) {cout << *hierarchy.right;}

        return out;
    }

};


#endif //CS368_A6_IRPSSHIERARCHY_H
