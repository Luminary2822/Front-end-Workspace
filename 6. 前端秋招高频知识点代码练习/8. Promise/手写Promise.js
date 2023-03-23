class Promise{
    constructor(executer){
        if(typeof executer !== 'function'){
            throw new TypeError("not a function")
        }
        this.value = null
        this.reason = null
        this.state = 'pending'
        const resolve = value => {
            if(this.state == 'pending'){
                this.state = 'fullfilled'
                this.value = value
            }
        }
        const reject = value => {
            if(this.state == 'pending'){
                this.state = 'rejected'
                this.reason = this.reason
            }
        }
        executer(resolve, reject)
    }
}