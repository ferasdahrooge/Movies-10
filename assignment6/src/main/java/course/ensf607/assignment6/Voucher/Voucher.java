package course.ensf607.assignment6.Voucher;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "voucher")
public class Voucher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voucherid;

    @Column(name = "refundamount")
    private Double refundamount;

    @Column(name = "datereceived")
    private Timestamp datereceived;

    @Column(name = "expirydate")
    private Timestamp expirydate;

    public Voucher(){

    }

    public Voucher(Double refundamount, Timestamp datereceived, Timestamp expirydate) {
        this.refundamount = refundamount;
        this.datereceived = datereceived;
        this.expirydate = expirydate;
    }

    public Long getVoucherid() {
        return voucherid;
    }

    public void setVoucherid(Long voucherid) {
        this.voucherid = voucherid;
    }

    public Double getRefundamount() {
        return refundamount;
    }

    public void setRefundamount(Double refundamount) {
        this.refundamount = refundamount;
    }

    public Timestamp getDatereceived() {
        return datereceived;
    }

    public void setDatereceived(Timestamp datereceived) {
        this.datereceived = datereceived;
    }

    public Timestamp getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(Timestamp expirydate) {
        this.expirydate = expirydate;
    }

    

    
}
